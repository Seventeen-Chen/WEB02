package com.uestc.mapper;


import com.uestc.pojo.Emp;
import com.uestc.pojo.EmpQueryParam;
import com.uestc.pojo.JobOption;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
//    Long count();
//
//    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc " +
//            "limit #{start}, #{pageSize} ")
//    List<Emp> list(Integer start, Integer pageSize);

    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id") // 允许使用自增主键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "+
    "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    /**
     * 统计员工性别信息
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 统计员工职位信息
     */
    List<Map<String, Object>> countEmpJobData();

    List<Emp> listAll();

    Emp getByUsernameAndPassword(Emp emp);
}
