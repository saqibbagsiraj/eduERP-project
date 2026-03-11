package com.eduerp.service;

import com.eduerp.entity.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty saveFaculty(Faculty faculty);

    List<Faculty> getAllFaculty();
}