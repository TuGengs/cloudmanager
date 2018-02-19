package com.halfmoon.cloudmanager.service.check.gradecheck;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.halfmoon.cloudmanager.dao.sql.check.gradecheck.impl.BuildingRoomInfoDao;
import com.halfmoon.cloudmanager.response.gradecheck.BuildingRoomInfo;


/**
 * @author LITAO
 * @time 下午2:10:43  2017年3月11日
 * @info 
 * @修改 重新导入BuildingRoomInfoDao，去掉了不需要的导包
 */
@Service
public class BuildingRoomInfoService {
	
	
	@Autowired
	private BuildingRoomInfoDao buildingRoomInfoDao;

	public BuildingRoomInfoDao getBuildingRoomInfoDao() {
		return buildingRoomInfoDao;
	}
	@Autowired
	public void setBuildingRoomInfoDao(BuildingRoomInfoDao buildingRoomInfoDao) {
		this.buildingRoomInfoDao = buildingRoomInfoDao;
	}
	
	/**
	 * 调用两个DAO层的方法，将返回得到的结果都组成json
	 * @param idList
	 * @return
	 * @throws IOException 
	 */
	public String getInfo(ArrayList<Integer> idList) throws IOException{
		
       ArrayList<String> RBuilding = buildingRoomInfoDao.getBuilding(idList);
      
       ArrayList<Building> result = new ArrayList<Building>();
       Building building;

       for (int i=0;i<RBuilding.size();i++){
           //填充building中的第一项：building_name
    	   building = new Building();
    	   building.setBuilding(RBuilding.get(i)); 
    	   
    	   ArrayList<String> RRoom = buildingRoomInfoDao.getRoom(RBuilding.get(i));
    	   Building.Room room;
    	   ArrayList<Building.Room> BR = new ArrayList<Building.Room>();
    	   for (int m=0;m<RRoom.size();m++){
    		   room = building.new Room(); 		 
    		   room.setRoom(RRoom.get(m));
    		   //调用数据库，得到值
    		   ArrayList<BuildingRoomInfo> RUser = buildingRoomInfoDao.getInfo(RBuilding.get(i), RRoom.get(m));
    		   //将从数据库中得到的内容，转成另一个list
    		   Building.Room.UserClass userClass;
    		   ArrayList<Building.Room.UserClass> BRU = new ArrayList<Building.Room.UserClass>();;
    		   for (int n=0;n<RUser.size();n++){
    			   userClass = room.new UserClass();	
    			   userClass.setId(RUser.get(n).getId());
    			   userClass.setName(RUser.get(n).getName());
    			   BRU.add(userClass);
    		   }
    		   room.setUserClass(BRU);		   
    		   BR.add(room);
    	   }	   
    	   //填充building中的第二项，arrayList
    	   building.setRoomClass(BR); 	 
    	   result.add(building);
       }

       Gson gson = new Gson();
       String str = gson.toJson(result);
		
		return str;
	}
	
}
class Building{
		private String building;
		public String getBuilding() {
			return building;
		}
		public void setBuilding(String building) {
			this.building = building;
		}
		private ArrayList<Room> roomClass;
	
		public ArrayList<Room> getRoomClass() {
			return roomClass;
		}
		public void setRoomClass(ArrayList<Room> roomClass) {
			this.roomClass = roomClass;
		}
		
		class Room{
			private String room;
			private ArrayList<UserClass> userClass;

			public ArrayList<UserClass> getUserClass() {
				return userClass;
			}
			public void setUserClass(ArrayList<UserClass> userClass) {
				this.userClass = userClass;
			}
			public String getRoom() {
				return room;
			}
			public void setRoom(String room) {
				this.room = room;
			}
			
		    class UserClass{
				int id;
				String name;
				public int getId() {
					return id;
				}
				public void setId(int id) {
					this.id = id;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
			}
		}
	}
