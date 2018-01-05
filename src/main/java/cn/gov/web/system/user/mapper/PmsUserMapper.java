package cn.gov.web.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.gov.web.system.user.entity.PmsUser;



public interface PmsUserMapper {
    
   
    /**
     * 新增用戶
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into pms_user VALUES (pms_user.id,pms_user.username,pms_user.remark)")
    @Options(useGeneratedKeys=true,keyProperty="pms_user.id")
    public int insertUser(@Param("pms_user")PmsUser pms_user) throws Exception;
    
    
    /**
     * 修改用戶
     * @param user
     * @param id
     * @return
     * @throws Exception
     */
    @Update(" update pms_user set username=#{u.username},remark=#{u.remark} where id=#{id}")
    public int updateUser (@Param("u")PmsUser user,@Param("id")int id) throws Exception;
    
     /**
      * 刪除用戶
      * @param id
      * @return
      * @throws Exception
      */
    @Delete(" delete from pms_user where id=#{id}  ")
    public int deleteUser(int id) throws Exception;
    
    
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    
    @Select(" select * from pms_user where id=#{id}")
    @Results({
        
        @Result(id=true,property="id",column="id",javaType=Integer.class),
        @Result(property="username",column="username",javaType=String.class),
        @Result(property="remark",column="remark",javaType=String.class)
    })
    public PmsUser selectUserById(int id) throws Exception;
     /**
      * 查询所有的用户信息
      * @return
      * @throws Exception
      */
    
    @Select(" select * from pms_user")
    @ResultMap("userMap")
    public List<PmsUser> selectAllUser() throws Exception;
  
    
    
}