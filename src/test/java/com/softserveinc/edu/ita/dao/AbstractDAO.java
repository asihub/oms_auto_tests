

package com.softserveinc.edu.ita.dao;

import com.softserveinc.edu.ita.dao.DAOException;
import com.softserveinc.edu.ita.dao.interfaces.IGenericDAO;
import com.softserveinc.edu.ita.enums.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * represents some general methods
 *
 * @param <T> type of using domains
 */
public abstract class AbstractDAO<T> implements IGenericDAO<T> {

    protected Connection connection;

    protected abstract String getSelectQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DAOException;

    /**
     * gets records from database by their ID
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public T getById(int id) throws DAOException {
        List<T> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE users.Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (T) list;
    }

    /**
     * gets  records from database by their RoleName
     *
     * @return
     * @throws DAOException
     */

    public T getByRoleName(Roles roleName) throws DAOException {
        List<T> list;
        String selectQuery = getSelectQuery();
        selectQuery += " WHERE RoleName = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, roleName.toString());
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return (T) list;
    }

    /**
     * gets all records from database
     *
     * @return
     * @throws DAOException
     */
    @Override
    public List<T> getAll() throws DAOException {
        List<T> list;
        String selectQuery = getSelectQuery();
        selectQuery += " ORDER BY ID DESC";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    /**
     * gets records from database for their login
     *
     * @param login
     * @return
     * @throws DAOException
     */
    public T getByLogin(String login) throws DAOException {
        List<T> list;
        String sqlQuery = getSelectQuery();
        sqlQuery += " WHERE Login= ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DAOException("Record with PK = " + login + " not found.");
        }
        if (list.size() > 1) {
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}