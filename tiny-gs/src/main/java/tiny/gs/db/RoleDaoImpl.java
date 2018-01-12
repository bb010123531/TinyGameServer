package tiny.gs.db;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import org.tiny.auto.bean.Role;

@Repository("roleDaoImpl")  
public class RoleDaoImpl implements RoleDao{

	@Resource(name="mongoTemplate")
    private MongoTemplate mongoTemplate;  
  
    @Override  
    public void insert(Role object,String collectionName) {  
        mongoTemplate.insert(object, collectionName);  
    }  
  
    @Override  
    public Role findOne(Map<String,Object> params,String collectionName) {  
         return mongoTemplate.findOne(new Query(Criteria.where("id").is(params.get("id"))), Role.class,collectionName);    
    }  
  
    @Override  
    public List<Role> findAll(Map<String,Object> params,String collectionName) {  
        List<Role> result = mongoTemplate.find(new Query(Criteria.where("age").lt(params.get("maxAge"))), Role.class,collectionName);  
        return result;  
    }  
  
    @Override  
    public void update(Map<String,Object> params,String collectionName) {  
        mongoTemplate.upsert(new Query(Criteria.where("id").is(params.get("id"))), new Update().set("name", params.get("name")), Role.class,collectionName);  
    }  
  
    @Override  
    public void createCollection(String name) {  
        mongoTemplate.createCollection(name);  
    }  
  
    @Override  
    public void remove(Map<String, Object> params,String collectionName) {  
        mongoTemplate.remove(new Query(Criteria.where("id").is(params.get("id"))),Role.class,collectionName);  
    }  
}
