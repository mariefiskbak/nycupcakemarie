package dat.nycupcakemarie.model.persistence;

import dat.nycupcakemarie.model.entities.User;
import dat.nycupcakemarie.model.exceptions.DatabaseException;

public interface IUserMapper
{
    public User login(String email, String kodeord) throws DatabaseException;
    public User createUser(String email, String password, int roleId, String firstname, String surname, int balance, int phoneNo) throws DatabaseException;
}
