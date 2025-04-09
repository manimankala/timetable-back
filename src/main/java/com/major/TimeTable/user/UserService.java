package com.major.TimeTable.user;

import com.major.TimeTable.common.Constants;
import com.major.TimeTable.loginCred.LoginDetails;
import com.major.TimeTable.loginCred.LoginDetailsRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    private final LoginDetailsRepo loginDetailsRepo;
    private final UserRepo userRepo;
    private final UserAttendanceRepo userAttendanceRepo;

    @Autowired
    public UserService(UserRepo userRepo, LoginDetailsRepo loginDetailsRepo,UserAttendanceRepo userAttendanceRepo) {
        this.userRepo = userRepo;
        this.loginDetailsRepo = loginDetailsRepo;
        this.userAttendanceRepo = userAttendanceRepo;
    }


    public User createUser(User user) {
        LoginDetails loginDetails = new LoginDetails();
        if(userRepo.findByEmail(user.getContactDetails().getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        if(user.getRole()== Constants.Role.TEACHER){
            UserAttendance attendance=  new UserAttendance();
            attendance.setUserId(user.getId());
            attendance.setDateTime(new ArrayList<>());
            userAttendanceRepo.save(attendance);
        }
        userRepo.save(user);
        String defPas = user.getName() + "@" + user.getContactDetails().getPhone().substring(6, 10);
        loginDetails.setUserId(user.getId());
        loginDetails.setUsername(user.getContactDetails().getEmail());
        loginDetails.setPassword(passwordEncoder.encode(defPas));
        loginDetails.setRole(user.getRole());
        loginDetailsRepo.save(loginDetails);
        return user;
    }

    public String updateUser(User user) {
        Optional<User> user1 = userRepo.findById(user.getId());
        AtomicReference<User> user2 = new AtomicReference<>();
        if (user1.isPresent()) {
            user1.get().setName(user.getName());
            user1.get().setAddressDetails(user.getAddressDetails());
            user1.get().getContactDetails().setPhone(user.getContactDetails().getPhone());
            user1.get().getContactDetails().setWebsite(user.getContactDetails().getWebsite());
            user1.get().setSubject(user.getSubject());
            user1.get().setDepartment(user.getDepartment());
            user1.get().setDesignation(user.getDesignation());
            user1.get().setClassName(user.getClassName());
            user1.get().setSection(user.getSection());
            user1.get().setSemester(user.getSemester());
            user1.get().setBranch(user.getBranch());
            user1.get().setUpdatedTimeStamp(new Date());
            user2.set(user1.get());
            userRepo.save(user2.get());
            return "User Updated";
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    public String changePassword(String userName,String password) {//Admin can change the password
        LoginDetails loginDetails = loginDetailsRepo.findByUsername(userName);
        if (loginDetails == null) {
            throw new RuntimeException("User not found");
        } else {
            loginDetails.setPassword(passwordEncoder.encode(password));
            loginDetailsRepo.save(loginDetails);
            return "Password Changed";
        }
    }

    public List<User> filterUsers(UserFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT u FROM User u WHERE 1=1");
        if (filter.getUserId() != null) {
            jpql.append(" AND u.id = :userId");
        }
        if (filter.getBusinessId() != null) {
            jpql.append(" AND u.businessId = :businessId");
        }
        if (filter.getRole() != null) {
            jpql.append(" AND u.role = :role");
        }
        if (filter.getClassName() != null) {
            jpql.append(" AND u.className = :className");
        }
        if (filter.getSection() != null) {
            jpql.append(" AND u.section = :section");
        }
        if (filter.getSemester() != null) {
            jpql.append(" AND u.semester = :semester");
        }
        if (filter.getBranch() != null) {
            jpql.append(" AND u.branch = :branch");
        }
        if (filter.getName() !=null){
            jpql.append("AND u.name LIKE  :name");
        }
        if (filter.getSubject() != null) {
            jpql.append(" AND u.subject = :subject");
        }
        if (filter.getDesignation() != null) {
            jpql.append(" AND u.designation = :designation");
        }
        if (filter.getDepartment() != null) {
            jpql.append(" AND u.department = :department");
        }
        if (filter.getSortBy() != null && filter.getSortOrder() != null) {
            jpql.append(" ORDER BY u.").append(filter.getSortBy()).append(" ").append(filter.getSortOrder());
        }
        TypedQuery<User> query = entityManager.createQuery(jpql.toString(), User.class);

        if (filter.getUserId() != null) {
            query.setParameter("userId", filter.getUserId());
        }
        if (filter.getBusinessId() != null) {
            query.setParameter("businessId", filter.getBusinessId());
        }
        if (filter.getRole() != null) {
            query.setParameter("role", filter.getRole());
        }
        if (filter.getClassName() != null) {
            query.setParameter("className", filter.getClassName());
        }
        if (filter.getSection() != null) {
            query.setParameter("section", filter.getSection());
        }
        if (filter.getSemester() != null) {
            query.setParameter("semester", filter.getSemester());
        }
        if(filter.getName()!=null){
            query.setParameter("name",filter.getName());
        }
        if (filter.getBranch() != null) {
            query.setParameter("branch", filter.getBranch());
        }
        if (filter.getSubject() != null) {
            query.setParameter("subject", filter.getSubject());
        }
        if (filter.getDesignation() != null) {
            query.setParameter("designation", filter.getDesignation());
        }
        if (filter.getDepartment() != null) {
            query.setParameter("department", filter.getDepartment());
        }
        if(filter.getPageNo()!=null&&filter.getPageSize()!=null){
            query.setFirstResult((filter.getPageNo()-1)*filter.getPageSize());
            query.setMaxResults(filter.getPageSize());
        }
        return query.getResultList();
    }


    public String deleteUser(UUID id){
        if(userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return userRepo.findById(id).get().getRole().toString().toLowerCase()+" Deleted Successfully";
        }
        else {
            throw new RuntimeException("User not found");
        }
    }
}
