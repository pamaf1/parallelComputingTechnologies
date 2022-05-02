package Ex3;

import java.util.List;

public class Teacher implements Runnable {
    private final String teacherName;
    private final List<String> groupNames;
    private final Journal journal;

    public Teacher(String teacherName, List<String> groupNames, Journal journal) {
        this.groupNames = groupNames;
        this.teacherName = teacherName;
        this.journal = journal;
    }

    @Override
    public void run() {
        for (String groupName : groupNames) {
            for (Integer studentName : this.journal.groups.get(groupName).groupList.keySet()) {
                Double mark = (double) (Math.round(100 * Math.random() * 100)) / 100;
                journal.makeMark(groupName, studentName, mark + " (" + this.teacherName + ")");
            }
        }
    }
}
