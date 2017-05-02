/**
 * @copyright: Copyright (c) 2015-2020 jd.com All Rights Reserved
 * @file: ComplaintQueryObjectRepository.java project: demo-complaints
 * @creator: pan
 * @date: 17/4/25
 */


package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @see:
 * @author: pan
 * @version: 1.0
 * @createdate: 2017-04-25 15:25
 * @lastdate:
 */
@Repository
public interface ComplaintQueryObjectRepository extends JpaRepository<ComplaintQueryObject, String> {

}
