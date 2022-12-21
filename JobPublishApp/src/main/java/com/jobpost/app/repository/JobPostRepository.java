package com.jobpost.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobpost.app.entity.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

}
