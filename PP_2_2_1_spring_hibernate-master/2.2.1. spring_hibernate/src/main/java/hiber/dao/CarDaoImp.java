package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public User showOwner(String model, int series) {
        TypedQuery<User> showOwner = sessionFactory.getCurrentSession().createQuery("from User as u where u.userCar.model=:model and u.userCar.series=:series");
        showOwner.setParameter("model", model);
        showOwner.setParameter("series", series);
        return showOwner.getSingleResult();
    }

}
