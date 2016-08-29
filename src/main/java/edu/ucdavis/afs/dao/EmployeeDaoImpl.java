package edu.ucdavis.afs.dao;

import edu.ucdavis.afs.model.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
    @Override
    @Transactional
    public Employee getEmployeeById(Long employeeId) {
		// Implemented this method per requirements
        String empId = "SELECT e from Employee e WHERE id = :id";
        TypedQuery<Employee> emp = entityManager.createQuery(empId, Employee.class);
        q.setParameter("id", employeeId);
        try {
            Employee employee = emp.getSingleResult();
            return employee;
        } catch (Exception e) {
            return null;
        }
	}

    @Override
    @Transactional
    public List<Employee> findEmployees(String firstName, String lastName, String jobTitle) {
        // Implemented this method per requirements
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("lastName", lastName));
		criteria.add(Restrictions.eq("jobTitle", jobTitle));
        return (Employee) criteria.uniqueResult();
    }
	
	
	protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
	
    @Override
    @Transactional
    public Long saveEmployee(Employee employee) {
        // Implemented this method per requirements
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(employee);
		} catch (HibernateException he){
			return he.printStackTrace();
        }
		return employee;
    }
}
