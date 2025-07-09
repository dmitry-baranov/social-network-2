package com.dmitrii.socialnetwork.service

import com.dmitrii.socialnetwork.exception.UserNameAlreadyExistsException
import com.dmitrii.socialnetwork.model.User
import com.dmitrii.socialnetwork.service.dao.UserDao
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceSpec extends Specification {

    UserDao userDao = Mock()
    UserService userService = new UserService(userDao)
    Validator validator

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def "Registration with unique username should succeed"() {
        given: "Unique user"
        def user = new User(username: "newUser", firstName: "John")

        when: "Attempt to register the user"
        def result = userService.register(user)

        then: "User is saved"
        1 * userDao.saveUser(user) >> true
        result == user
    }

    def "Registration with existing username should throw exception"() {
        given: "User with existing username"
        def user = new User(username: "existingUser", firstName: "Jane")

        when: "Attempt to register"
        userService.register(user)

        then: "Exception is thrown"
        1 * userDao.saveUser(user) >> false
        thrown(UserNameAlreadyExistsException)
    }

    @Unroll
    def "Find user by ID: #testCase"() {
        given: "Mocked repository"
        def userId = UUID.fromString("6e7c5e3a-2f1b-4c0d-9a6f-3a1e8e2d4f5b")
        def expectedUser = new User(id: UUID.fromString("6e7c5e3a-2f1b-4c0d-9a6f-3a1e8e2d4f5b"), username: "testUser")

        when: "Searching for user"
        def result = userService.getUserById(userId)

        then: "Expected result is returned"
        1 * userDao.findById(userId) >> Optional.ofNullable(expectedUser)
        result == expectedUser
    }

    def "Find by non-existing ID should throw exception"() {
        given: "Non-existing ID"
        def invalidId = UUID.randomUUID()

        when: "Searching for user"
        userService.getUserById(invalidId)

        then: "Exception is thrown"
        1 * userDao.findById(invalidId) >> Optional.empty()
        thrown(NoSuchElementException)
    }

    def "Get user by username should return correct data"() {
        given: "Existing username"
        def username = "existingUser"
        def expectedUser = new User(username: username)

        when: "Searching by username"
        def result = userService.getUserByUsername(username)

        then: "User is found"
        1 * userDao.findByUsername(username) >> Optional.of(expectedUser)
        result == expectedUser
    }

    def "Exception when searching contains correct message"() {
        given: "Invalid ID"
        def invalidId = UUID.randomUUID()

        when: "Searching for user"
        userService.getUserById(invalidId)

        then: "Error message contains ID"
        1 * userDao.findById(invalidId) >> Optional.empty()
        def exception = thrown(NoSuchElementException)
        exception.message == "User not found by id: $invalidId"
    }
}
