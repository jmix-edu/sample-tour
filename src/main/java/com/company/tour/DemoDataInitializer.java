package com.company.tour;

import com.company.tour.app.TourExplorerUtils;
import com.company.tour.entity.Transport;
import com.company.tour.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.security.Authenticated;
import io.jmix.flowui.UiEventPublisher;
import io.jmix.flowui.backgroundtask.BackgroundTask;
import io.jmix.flowui.backgroundtask.BackgroundWorker;
import io.jmix.flowui.backgroundtask.TaskLifeCycle;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.company.tour.app.TourExplorerUtils.*;

@Component
public class DemoDataInitializer {
    private final DataManager dataManager;
    private final FileStorage fileStorage;
    private final BackgroundWorker backgroundWorker;
    private final UiEventPublisher uiEventPublisher;

    public DemoDataInitializer(DataManager dataManager,
                               FileStorage fileStorage,
                               BackgroundWorker backgroundWorker, UiEventPublisher uiEventPublisher) {
        this.dataManager = dataManager;
        this.fileStorage = fileStorage;
        this.backgroundWorker = backgroundWorker;
        this.uiEventPublisher = uiEventPublisher;
    }

    private static final Set<String> INITIAL_USERNAMES = Set.of("alan", "alex", "alice", "mary", "thomas",
            "paul", "linda", "susan", "sujin");
    private static final String AVATARS_PATH = "com/company/tour/avatars/";

    @EventListener
    @Authenticated
    public void onApplicationStarted(ApplicationStartedEvent event) {
        List<User> users = dataManager.load(User.class)
                .query("where e.username IN :usernames")
                .parameter("usernames", INITIAL_USERNAMES)
                .list();

        List<User> saveUsers = new ArrayList<>(users.size());
        for (User user : users) {
            if (user.getAvatar() != null) {
                continue;
            }
            FileRef fileRef = uploadPicture(user.getUsername() + ".png");
            user.setAvatar(fileRef);
            saveUsers.add(user);
        }
        dataManager.saveAll(saveUsers);
    }

    @EventListener
    public void onInteractiveAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        TransportUpdateTask task = new TransportUpdateTask(dataManager.unconstrained()
                .load(Transport.class).all().list());
        task.addProgressListener(new BackgroundTask.ProgressListenerAdapter<>() {
            @Override
            public void onProgress(List<TransportUpdateEvent> changes) {
                uiEventPublisher.publishEvent(changes.get(0));
            }
        });
        backgroundWorker.handle(task).execute();
    }

    private FileRef uploadPicture(String fileName) {
        ClassPathResource resource = new ClassPathResource(AVATARS_PATH + fileName);
        try (InputStream stream = resource.getInputStream()) {
            return fileStorage.saveStream(fileName, stream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read resource: " + AVATARS_PATH + fileName, e);
        }
    }

    static class TransportUpdateTask extends BackgroundTask<TransportUpdateEvent, Void> {
        private final List<Transport> transports;

        private Iterator<Coordinate> bigGardenTourIterator;
        private Iterator<Coordinate> berlinCenterTourIterator;
        private Iterator<Coordinate> fernsehturmTourIterator;

        public TransportUpdateTask(List<Transport> transports) {
            super(100, TimeUnit.DAYS);

            this.transports = transports;
        }

        @Override
        public Void run(TaskLifeCycle<TransportUpdateEvent> taskLifeCycle) throws Exception {
            while (true) {
                if (taskLifeCycle.isCancelled() || taskLifeCycle.isInterrupted()) {
                    return null;
                }
                Thread.sleep(2000);
                for (Transport transport : transports) {
                    taskLifeCycle.publish(new TransportUpdateEvent(transport.getId(), next(transport)));
                }
            }
        }

        private Coordinate next(Transport transport) {
            if (transport.getTour() == null) {
                return null;
            }
            if (BIG_GARDEN_TOUR.equals(transport.getTour().getName())) {
                if (bigGardenTourIterator == null || !bigGardenTourIterator.hasNext()) {
                    bigGardenTourIterator = TourExplorerUtils.BIG_GARDEN_TOUR_COORDINATES.iterator();
                }
                return bigGardenTourIterator.next();
            } else if (BERLIN_CENTER_TOUR.equals(transport.getTour().getName())) {
                if (berlinCenterTourIterator == null || !berlinCenterTourIterator.hasNext()) {
                    berlinCenterTourIterator = TourExplorerUtils.BERLIN_CENTER_TOUR_COORDINATES.iterator();
                }
                return berlinCenterTourIterator.next();
            } else if (FERNSEHTURM_TOUR.equals(transport.getTour().getName())) {
                if (fernsehturmTourIterator == null || !fernsehturmTourIterator.hasNext()) {
                    fernsehturmTourIterator = FERNSEHTURM_TOUR_COORDINATES.iterator();
                }
                return fernsehturmTourIterator.next();
            }
            return null;
        }
    }

    public static class TransportUpdateEvent extends ApplicationEvent {
        private Coordinate coordinate;

        public TransportUpdateEvent(UUID transportId, Coordinate coordinate) {
            super(transportId);

            this.coordinate = coordinate;
        }

        public UUID getTransportId() {
            return (UUID) getSource();
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }

        public Double getX() {
            return coordinate == null ? null : coordinate.getX();
        }

        public Double getY() {
            return coordinate == null ? null : coordinate.getY();
        }
    }
}