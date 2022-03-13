package ro.societateahermes.backendservice.utils.mapper;

import org.mapstruct.Mapper;
import ro.societateahermes.backendservice.entities.Timeline;
import ro.societateahermes.backendservice.entities.dto.TimelineDTO;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface TimelineMapperInterface {

    TimelineDTO convertToTimelineDTO(Timeline timeline);

    Timeline convertToTimeline(TimelineDTO timelineDTO);

    List<TimelineDTO> convertToTimelineDTOS(List<Timeline> timelines);

    List<Timeline> convertToTimelines(List<TimelineDTO> timelineDTOS);
}
