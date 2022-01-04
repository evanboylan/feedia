package com.wcci.Feedia.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
public class GoogleCalendar {

    @GeneratedValue
    @Id
    protected long id;

    protected String googleCalendarId;

    @OneToOne
    @JsonIgnore
    protected Reptile reptile;

    private static final String APPLICATION_NAME = "Feedia";

    public void setReptile(Reptile reptile) {
        this.reptile = reptile;
    }

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    public String getGoogleCalendarId() {
        return googleCalendarId;
    }

    public long getId() {
        return id;
    }

    public Reptile getReptile() {
        return reptile;
    }

    public GoogleCalendar() {
            this.googleCalendarId = "0";
    }

    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleCalendar.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void createCalendar(String calendarName) throws IOException, GeneralSecurityException{
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        AclRule rule = new AclRule();
        AclRule.Scope scope = new AclRule.Scope();
        scope.setType("default");
        rule.setScope(scope).setRole("reader");

        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(calendarName);
        calendar.setTimeZone("America/New_York");

        com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();
        googleCalendarId = createdCalendar.getId();
        AclRule createdRule = service.acl().insert(googleCalendarId, rule).execute();
    }

    public void getNextEventTime() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();


        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(googleCalendarId)
                .setMaxResults(1)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                String startString = start.toStringRfc3339();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
                try {
                    Date startDate = sdf.parse(startString);
                    long difference = startDate.getTime() - System.currentTimeMillis();
                    long minutesUntil = TimeUnit.MILLISECONDS.toMinutes(difference);
                    System.out.println(minutesUntil + " minutes until your next event");
                    if (minutesUntil <= 15) {
                        System.out.println("Indicator light on");
                    } else {
                        System.out.println("Indicator light off");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }

    public void createEvent(String eventSummary, String eventLocation, String eventDescription, String eventStartTime, String eventEndTime, String eventFrequency, String eventRepetition) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event testEvent = new Event()
                .setSummary(eventSummary)
                .setLocation(eventLocation)
                .setDescription(eventDescription);
        DateTime startDateTime = new DateTime(eventStartTime);
        // "2021-12-02T12:30:00.000-05:00"
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/New_York");
        testEvent.setStart(start);

        DateTime endDateTime = new DateTime(eventEndTime);
        // "2021-12-03T15:00:00.000-05:00"
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/New_York");
        testEvent.setEnd(end);
        String[] recurrence = new String[] {"RRULE:FREQ="+eventFrequency+";COUNT="+eventRepetition};
        testEvent.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("feediaapplication@gmail.com"),
        };
        testEvent.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        testEvent.setReminders(reminders);

        String calendarId = googleCalendarId;
        testEvent = service.events().insert(calendarId, testEvent).execute();
        System.out.printf("Event created: %s\n", testEvent.getHtmlLink());

    }
}
