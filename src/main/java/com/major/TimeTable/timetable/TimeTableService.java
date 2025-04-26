package com.major.TimeTable.timetable;

import com.major.TimeTable.common.Constants;
import com.major.TimeTable.user.User;
import com.major.TimeTable.user.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TimeTableService {

    @Autowired
    private EntityManager entityManager;

    private  final TimeTableRepo timeTableRepo;
    private final UserRepo userRepo;

    @Autowired
    public TimeTableService(TimeTableRepo timeTableRepo, UserRepo userRepo) {
        this.timeTableRepo = timeTableRepo;
        this.userRepo = userRepo;
    }

    public TimeTable createTimeTable(TimeTable timeTable) {
        DateTimeFormatter format=DateTimeFormatter.ofPattern("hh:mm:a");
        if(timeTable.getCreatedById()!=null){
            Optional<User> user=userRepo.findById(timeTable.getCreatedById());
            if(user.isPresent()){
                if(user.get().getRole()== Constants.Role.ADMIN) {
                    for(TimeTableEntry entry:timeTable.getElemList()){
                        entry.setFromTime(LocalTime.parse(entry.getFromTimeInput(),format));
                        entry.setToTime(LocalTime.parse(entry.getToTimeInput(),format));
                    }
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

    public TimeTable updateTimeTable(TimeTable timeTable) {
        if(timeTable.getCreatedById()!=null){
            Optional<User> user=userRepo.findById(timeTable.getCreatedById());
            if(user.isPresent()){
                if(user.get().getRole()== Constants.Role.ADMIN) {
                    AtomicReference<TimeTable> tt=new AtomicReference<>();
                    Optional<TimeTable> existingTimeTable = timeTableRepo.findById(timeTable.getId());
                    existingTimeTable.ifPresent(e -> {
                        e.setName(timeTable.getName());
                        e.setCreatedBy(timeTable.getCreatedBy());
                        e.setCreatedById(timeTable.getCreatedById());
                        e.setElemList(timeTable.getElemList());
                        e.setBranch(timeTable.getBranch());
                        e.setClassName(timeTable.getClassName());
                        e.setSemester(timeTable.getSemester());
                        e.setSection(timeTable.getSection());
                        e.setUpdatedTimeStamp(timeTable.getUpdatedTimeStamp());
                        tt.set(e);
                    });
                    return timeTableRepo.save(tt.get());
                }else {
                    throw new RuntimeException("User is not authorized to update timetable");
                }
            }else {
                throw new RuntimeException("User not found");
            }
        }
        return null;
    }

    public List<TimeTable> getFilterTimeTable(TimeTableFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT t FROM TimeTable t WHERE 1=1 ");
        if (filter.getBranch() != null) {
            jpql.append("AND t.branch = :branch ");
        }
        if (filter.getSemester() != null) {
            jpql.append("AND t.semester = :semester ");
        }
        if (filter.getSection() != null) {
            jpql.append("AND t.section = :section ");
        }
        if (filter.getSubject() != null) {
            jpql.append("AND t.subject LIKE :subject ");
        }
        if (filter.getClassName() != null) {
            jpql.append("AND t.className = :className ");
        }

        TypedQuery<TimeTable> typedQuery = entityManager.createQuery(jpql.toString(), TimeTable.class);
        if (filter.getBranch() != null) {
            typedQuery.setParameter("branch", filter.getBranch());
        }
        if (filter.getSemester() != null) {
            typedQuery.setParameter("semester", filter.getSemester());
        }
        if (filter.getSection() != null) {
            typedQuery.setParameter("section", filter.getSection());
        }
        if (filter.getSubject() != null) {
            typedQuery.setParameter("subject", "%" + filter.getSubject() + "%");
        }
        if (filter.getClassName() != null) {
            typedQuery.setParameter("className", filter.getClassName());
        }

        return typedQuery.getResultList();
    }
    public String deleteTimeTable(UUID id){
        if(timeTableRepo.findById(id).isPresent()) {
            String result= timeTableRepo.findById(id).get().getName().toLowerCase();
            timeTableRepo.deleteById(id);
            return result+" Deleted Successfully";
        }
        else {
            throw new RuntimeException("User not found");
        }
    }
}
