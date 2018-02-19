package com.halfmoon.cloudmanager.service.check.gradecheck;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl.GradingItemDao;
import com.halfmoon.cloudmanager.model.check.GradingItem;

/**
 * @author LITAO
 * @time 下午2:11:57  2017年3月11日
 * @info 
 * @修改 去掉了所有的org.json包，统一换成Gson
 * @修改 重新导包
 */
@Service
public class GradingItemService {
	@Autowired
	private GradingItemDao gradingItemDao;
	
	@Autowired
	public void setGradingItemDao(GradingItemDao gradingItemDao) {
		this.gradingItemDao = gradingItemDao;
	}
	
	public GradingItemDao getGradingItemDao() {
		return gradingItemDao;
	}

	/**
	 * 用catch捕捉异常，如果有问题，则返回false，表示存入不成功
	 * 
	 * @param gradingCheckId
	 * @param name
	 * @param grades
	 * @return 是否存入成功
	 */
	public boolean isAddItem(int gradingCheckId, String name, float grades){
		boolean isAdd = false;
		System.out.println("in isAddItem");
		try{
			//gradingItemDao = new GradingItemDao(); //!!!!!!
			if (gradingItemDao == null){
				System.out.println("gradingItemDao is null");
			}
			isAdd = gradingItemDao.addItem(gradingCheckId, name, grades);
			System.out.println("isAdd:" + isAdd);
		}catch (Exception e){
			e.printStackTrace();
		}
		return isAdd;
	}
	
	
	/**
	 * 传入检查项的自增id，去删id对应的整个item
	 * 
	 * @param id
	 * @return
	 */
	public boolean isDeleteItem(int id){
		
		boolean isDelete = false;
		
		try{
			isDelete = gradingItemDao.deleteItemById(id);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		return isDelete;
	}
	
	
	/**
	 * 传入id，得到项目
	 * 
	 * @param gradingCheckId
	 * @return
	 */
	public ArrayList<Integer> getItemId(int grading_check_id){
		
		ArrayList<Integer> result = null;
		try{
			result = gradingItemDao.getItemId(grading_check_id);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	
	/**
	 * 通过这个函数去查找对应的auto_id，name和grades 最后在service中组成json传到前端
	 * 
	 * @param gradingCheckId
	 * @return
	 */
	public ArrayList<GradingItem> getItem(int gradingCheckId){
		ArrayList<GradingItem> result = new ArrayList<GradingItem>();
		
		try{
			result = gradingItemDao.getItem(gradingCheckId);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	
	/**
	 * 更新（修改）某一条扣分项的性质 提供这种方法，但是不一定使用
	 * @param id
	 * @param name
	 * @param grades
	 * @return
	 */
	public boolean isUpdateItem(int id, String name, float grades){
		boolean isUpdate = false;
		try{
			isUpdate = gradingItemDao.updateItem(id, name, grades);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return isUpdate;
	}
	
/**
 * ****************************************************************************************************************8
 * 以下代码，在测试中用过，目前都被注释掉了
 */
	
	/**
	 * 方法测试，实际未用
	 * 
	 * 在这里将从getItem()获得的arraylist打包成json
	 * 这里用的json包是org.json
	 * json中包含的数据：name。id，grades
	 */
//	public JSONArray itemJson(ArrayList<GradingItem> list){
//		
//		JSONArray result = new JSONArray();
//		
//		for (int i=0;i<list.size();i++){
//			System.out.println(i);
//			JSONObject json = new JSONObject();
//			json.put("id", list.get(i).getId());
//			json.put("name", list.get(i).getName());
//			json.put("grades", list.get(i).getGrades());
//			result.put(json);
//			System.out.println(json);
//		}
//		
//		return result;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*----------Dragon be here!----------/
	/* 　 　┏┓　　　┏┓
	/* 　　┏┛┻━-━━┛┻┓
	/* 　　┃　　　   　　┃
	/* 　　┃　　   ━　 　┃
	/* 　　┃　┳┛　┗┳　┃
	/* 　　┃　　　　　　┃
	/* 　　┃　　　┻　 　┃
	/* 　　┃　　　　　　┃
	/* 　　┗━┓　　　┏━┛
	/*　    　　　┃　　　┃神兽保佑
	/* 　  　┃　　　┃代码无BUG！
	/* 　　　┃　　　┗━━━┓
	/* 　　　┃　　　　　　┣┓
	/*　    　　　┃　　　　　　┏┛
	/* 　　　┗┓┓┏━-┳┓┏┛
	/* 　　 　┃┫┫　  ┃┫┫
	/* 　 　　┗┻┛　  ┗┻┛
	* ━━━━━━神兽出没━━━━━━*/
	
}
