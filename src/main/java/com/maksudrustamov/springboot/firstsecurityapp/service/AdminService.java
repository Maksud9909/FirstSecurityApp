package com.maksudrustamov.springboot.firstsecurityapp.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    // к этому методу имеет доступ только админ
    // он всегда будет проверять, что у человека была ли эта роль
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStuff(){
        System.out.println("Only admin here");
    }
}
