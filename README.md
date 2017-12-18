# introsde-2017-assignment-3-server

Federico Baldessari | federico.baldessari@studenti.unitn.it

[Heroku Link](https://introsde-3.herokuapp.com/people)
[Github Client](https://github.com/balde73/introsde-2017-assignment-3-client)


## Project
The Server is divided in four packages:
- `introsde.assignment3.dao`: Contains the object and interface to access the database and persist data. As in the second assignment, every function that handle the persistence of data is here. This allow to have simpler Entity and provide a better distinction between entities and daos. Also generic methods like `findById`, `findAll`, `add`... are in an abstract JpaDao that every other Dao extends.
- `introsde.assignment3.endpoint`: The `PeoplePublisher` allow to publish the People Service on localhost
- `introsde.assignment3.model`: The classes that represents the data model: `Activity`, `ActivityType` and `Person`
- `introsde.assignment3.soap`: Contains the interface and the corresponding implementation of the SOAP service

All the 12 Methods are provided.
> Method #8: readPersonPreference**s**(Long id, Long activity_id) => Preference

is renamed to:

> Method #8: readPersonPreference(Long id, Long activity_id) => Preference

since it reads only **one** specific preference.

## Execution

Clone the repository and enter the directory
```
git clone https://github.com/balde73/introsde-2017-assignment-3-server.git && cd introsde-2017-assignment-3-server
```

To deploy locally simply run
```
ant start
// WSDL: http://localhost:6902/people?wsdl
```

If you want to deploy on heroku
```
ant create.war
heroku login
heroku create <HEROKU_APP_NAME> --region eu
heroku war:deploy <WAR_PATH> --app <HEROKU_APP_NAME>
heroku open
// WSDL: http://<HEROKU_APP_NAME>/people?wsdl
```
NOTES: `ant create.war` generates a war named `deploy.war`

## Additional Notes

It was not possible to use the ant buildpack at https://github.com/IntroSDE/heroku-buildpack-ant due to "JAVA_HOME not defined correctly". It seems like java is not installed inside the application: `whereis java` returned no value. So an alternative solution is provided to deploy on Heroku.
