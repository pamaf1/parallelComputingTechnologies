package Ex3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Journal {
    public HashMap<String, Group> groups = new HashMap<>();

    public Journal() {
        Group group1 = new Group("ІT-92", 30);
        Group group2 = new Group("ІT-93", 31);
        Group group3 = new Group("ІT-94", 33);

        this.groups.put(group1.getGroupName(), group1);
        this.groups.put(group2.getGroupName(), group2);
        this.groups.put(group3.getGroupName(), group3);
    }

    public void makeMark(String groupName, Integer studentName, String mark) {
        synchronized (this.groups.get(groupName).groupList.get(studentName)) {
            this.groups.get(groupName).groupList.get(studentName).add(mark);
        }
    }

    public void show() {
        for (String groupName : groups.keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.printf("Group name: %6s\n", groupName);
            for (Integer studentName :
                    groups.get(groupName).groupList.keySet().stream().sorted().collect(Collectors.toList())) {
                System.out.printf("Student %5s", studentName);
                Iterator<String> it = groups.get(groupName).groupList.get(studentName).iterator();
                while(it.hasNext()){
                    String mark = it.next();
                    System.out.printf("%30s", mark);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}