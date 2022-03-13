package ro.societateahermes.backendservice.utils.mapper.mapperImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.societateahermes.backendservice.entities.Timeline;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;
import ro.societateahermes.backendservice.utils.mapper.ActivityMapperInterface;
import ro.societateahermes.backendservice.utils.mapper.TimelineMapperInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimelineMapperImplementation implements TimelineMapperInterface {

    private final ActivityMapperInterface activityMapper;

    @Autowired
    public TimelineMapperImplementation(ActivityMapperInterface activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Override
    public TimelineDTO convertToTimelineDTO(Timeline timeline) {
        if(timeline == null)
            return null;

        TimelineDTO timelineDTO = new TimelineDTO();

        timelineDTO.setIdTimeline(timeline.getIdTimeline());
        timelineDTO.setIdEvent(timeline.getIdEvent());
        timelineDTO.setListOfActivities(activityMapper.convertToActivityDTOS(timeline.getListOfActivities()));

        return timelineDTO;
    }

    @Override
    public Timeline convertToTimeline(TimelineDTO timelineDTO) {
        if(timelineDTO == null)
            return null;

        Timeline timeline = new Timeline();

        timeline.setIdTimeline(timelineDTO.getIdTimeline());
        timeline.setIdEvent(timelineDTO.getIdEvent());
        timeline.setListOfActivities(activityMapper.convertToActivities(timelineDTO.getListOfActivities()));

        return timeline;
    }

    @Override
    public List<TimelineDTO> convertToTimelineDTOS(List<Timeline> timelines) {

        if (timelines == null )
            return null;

        List<TimelineDTO> timelineDTO = new ArrayList<TimelineDTO>(timelines.size());
        for (Timeline timeline : timelines ) {
            timelineDTO.add(convertToTimelineDTO(timeline));
        }

        return timelineDTO;
    }

    @Override
    public List<Timeline> convertToTimelines(List<TimelineDTO> timelineDTOS) {

        if (timelineDTOS == null )
            return null;

        List<Timeline> timelines = new ArrayList<Timeline>(timelineDTOS.size());
        for (TimelineDTO timelineDTO : timelineDTOS ) {
            timelines.add(convertToTimeline(timelineDTO));
        }

        return timelines;
    }
}
