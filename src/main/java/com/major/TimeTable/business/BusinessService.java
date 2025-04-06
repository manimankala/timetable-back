package com.major.TimeTable.business;

import com.major.TimeTable.common.Constants;
import com.major.TimeTable.user.User;
import com.major.TimeTable.user.UserRepo;
import com.major.TimeTable.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BusinessService {
    private final BusinessRepo businessRepo;
    private final UserService userService;
    public BusinessService(BusinessRepo businessRepo,UserService userService) {
        this.businessRepo = businessRepo;
        this.userService=userService;
    }

    public Business saveBusiness(Business business) {
        Business temp= businessRepo.save(business);
        User user=new User();
        user.setRole(Constants.Role.ADMIN);
        user.setBusinessId(temp.getId());
        user.setBusinessName(temp.getName());
        user.getContactDetails().setEmail("admin@gmail.com");
        user.getContactDetails().setPhone("1234567890");
        user.setName("Admin");
        userService.createUser(user);
        return temp;
    }

    public List<Business> getAll() {
        return businessRepo.findAll();
    }

    public String deleteBusiness(UUID id) {
        businessRepo.deleteById(id);
        return "Business Deleted";
    }

}
