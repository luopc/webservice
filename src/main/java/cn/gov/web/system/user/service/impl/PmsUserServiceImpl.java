package cn.gov.web.system.user.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.gov.web.system.user.entity.PmsUser;
import cn.gov.web.system.user.mapper.PmsUserMapper;
import cn.gov.web.system.user.service.IPmsUserService;

@Service
public class PmsUserServiceImpl implements IPmsUserService {
	@Resource
	private PmsUserMapper userMapper;

	@Override
	public PmsUser test(String username, String remark) {
		Random rd = new Random();
		PmsUser user = new PmsUser();
		user.setId(rd.nextInt(123456));
		user.setUsername(username);
		user.setUsername(remark);
		try {
			List<PmsUser> list = userMapper.selectAllUser();
			System.out.println(list.size());
			if(!list.isEmpty())return list.get(00);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
