package cn.itcast.bos.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.page.PaginationInfo;
import cn.itcast.bos.service.StaffService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class StaffServiceTest {
	
	@Autowired
	private StaffService staffService;

	@Test
	public void testSaveStaff() {
		Staff staff = new Staff();
		staff.setId("QP400");
		staff.setName("小小");
		
		staffService.saveStaff(staff);
	}
	
	@Test
	public void pageQuery(){
		PaginationInfo<Staff> paginationInfo = new PaginationInfo<Staff>();
		paginationInfo.setPageno(2);
		paginationInfo.setPagesize(2);
		
		staffService.pageQuery(paginationInfo);
		System.out.println(paginationInfo.getTotal());
		System.out.println(paginationInfo.getRows());
	}

}
