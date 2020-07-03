// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.List;
import java.lang.*;
import java.util.Comparator;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<TimeRange> meetingTimes = new ArrayList<TimeRange>();

    //Request information
    long requestDuration = request.getDuration();
    Collection<String> requestAttendees = request.getAttendees();

    // Traverse collection of events to get attendees, and time range of each relevant event
    for(Event event: events){
        TimeRange eventRange = event.getWhen();

        // Modify copy of Event Attendees to only include relevant attendees that overlap with the request
        Set<String> eventAttendees = event.getAttendees();
        Set<String> copy = new HashSet<>();
        copy.addAll(eventAttendees);
        copy.retainAll(requestAttendees);
        
        // If any of the attendees overlap with this event's attendees, then this duration is unavailable  
        if (!(copy.isEmpty())){
            meetingTimes.add(eventRange);
        }
    }

    // Convert Collection of TimeRanges into a List in order to sort in ascending order
    List<TimeRange> listMeetingTimes = new ArrayList<TimeRange>(meetingTimes);
    Collections.sort(listMeetingTimes, TimeRange.ORDER_BY_START);

    // Final Collection to hold available time ranges for the requested meeting
    Collection<TimeRange> availableTimes = new ArrayList<TimeRange>();

    int start = TimeRange.START_OF_DAY;
    int end = TimeRange.END_OF_DAY;
    
    for(TimeRange range: listMeetingTimes){
        // Create time range in between scheduled times if there is enough time allocated
        if((range.start() > start) && ((range.start() - start) >= requestDuration)){
            TimeRange timeTemp = TimeRange.fromStartEnd(start, range.start(), false);
            availableTimes.add(timeTemp);
            start = range.end();
        }else{
            // Ensures the latest end time for overlapping events
            start = Math.max(start, range.end());
        }
    }

    // Checks if there is enough time between the end of the last event in listMeetingTimes and the end of day to create a time range
    if((start != end+1) && ((end+1 - start) >= requestDuration)){
        TimeRange timeTemp = TimeRange.fromStartEnd(start, end, true);
        availableTimes.add(timeTemp);
    }

    return availableTimes;
  }
}
