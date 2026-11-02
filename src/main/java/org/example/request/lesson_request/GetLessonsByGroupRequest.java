package org.example.request.lesson_request;

public class GetLessonsByGroupRequest {
    private final String startDate;
    private final String endDate;
    private final long groupId;

    public GetLessonsByGroupRequest(String startDate, String endDate, long groupId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupId = groupId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public long getGroupId() {
        return groupId;
    }
}
