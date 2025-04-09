package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Constants;
import com.major.TimeTable.user.User;
import com.major.TimeTable.user.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeTableService {

    private  final TimeTableRepo timeTableRepo;
    private final UserRepo userRepo;

    public TimeTableService(TimeTableRepo timeTableRepo, UserRepo userRepo) {
        this.timeTableRepo = timeTableRepo;
        this.userRepo = userRepo;
    }

    public TimeTable createTimeTable(TimeTable timeTable) {
        if(timeTable.getCreatedById()!=null){
            Optional<User> user=userRepo.findById(timeTable.getCreatedById());
            if(user.isPresent()){
                if(user.get().getRole()== Constants.Role.ADMIN) {
                    return timeTableRepo.save(timeTable);
                }else {
                    throw new RuntimeException("User is not authorized to create timetable");
                }
            }else {
                throw new RuntimeException("User not found");
            }
        }
        return null;

    }

    public List<TimeTable> getFilterTimeTable(TimeTableFilter filter){
        String query= null;
        if(filter.getBranch()!=null){
            query="SELECT timeTable from TimeTable timeTable where timeTable.branch=:branch";
        }
        if(filter.getSemester()!=null){
            query="SELECT timeTable from TimeTable timeTable where timeTable.semester=:semester";
        }
        if(filter.getSection()!=null){
            query="SELECT timeTable from TimeTable timeTable where timeTable.section=:section";
        }
        if(filter.getSubject()!=null) {
            query = "SELECT timeTable from TimeTable timeTable where timeTable.subject=:subject";
        }

        return null;
    }
}
