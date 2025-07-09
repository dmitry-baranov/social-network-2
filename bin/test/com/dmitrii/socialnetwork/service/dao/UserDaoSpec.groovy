package com.dmitrii.socialnetwork.service.dao

import com.dmitrii.socialnetwork.TestcontainersBase
import com.dmitrii.socialnetwork.model.User
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Stepwise

import java.time.LocalDate

@Stepwise
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserDaoSpec extends TestcontainersBase {

    @Autowired
    private UserDao userDao

    def "should save user with UUID"() {
        when: "saving the user"
        def testUser = getTestUser()
        boolean result = userDao.saveUser(testUser)

        then: "operation is successful"
        result

        and: "user is available by UUID"
        Optional<User> savedUser = userDao.findById(testUser.id)
        savedUser.isPresent()
        savedUser.get().username == testUser.username
    }

    def "should find user by username"() {
        given: "user is saved"
        def testUser = getTestUser()
        userDao.saveUser(testUser)

        when: "searching by username"
        Optional<User> result = userDao.findByUsername(testUser.username)

        then: "user is found"
        result.isPresent()
        result.get().id == testUser.id
    }

    def "should enforce field length constraints"() {
        given: "user with maximum field lengths"
        User maxLengthUser = User.builder()
                .id(UUID.randomUUID())
                .username("a" * 22)
                .firstName("A" * 21)
                .passwordHash("\$2a\$12\$5Z8zM6L9Yf7q1w0Kj3rT.6VbQeDcXwRtjJ7mNpOqRsSvk1S2hL5G")
                .build()

        when: "saving the user"
        boolean result = userDao.saveUser(maxLengthUser)

        then: "get an exception on save"
        def exception = thrown(DataIntegrityViolationException)
        exception.message.contains("value too long for type character varying(21)")
    }

    def "should handle NULL in optional fields"() {
        given: "user without optional fields"
        User minimalUser = User.builder()
                .id(UUID.randomUUID())
                .username("minimal_user")
                .firstName("Min")
                .passwordHash("\$2a\$12\$5Z8zM6L9Yf7q1w0Kj3rT.6VbQeDcXwRtjJ7mNpOqRsSvk1S2hL5G")
                .build()

        when: "saving the user"
        boolean result = userDao.saveUser(minimalUser)

        then: "operation is successful"
        result

        and: "fields contain NULL"
        Optional<User> retrieved = userDao.findById(minimalUser.id)
        retrieved.get().with {
            assert lastName == null
            assert birthdate == null
            assert biography == null
            assert city == null
        }
    }

    def "should throw error on duplicate username"() {
        given: "existing user"
        def testUser = getTestUser()
        userDao.saveUser(testUser)

        when: "creating a user with the same username"
        User duplicateUser = getTestUser()
        duplicateUser.id = testUser.id

        userDao.saveUser(duplicateUser)

        then: "get a uniqueness constraint violation exception"
        def exception = thrown(DuplicateKeyException)
        exception.message.contains("username")
    }

    private static User getTestUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .username("john_doe_${UUID.randomUUID().toString().substring(0, 8)}")
                .firstName("John")
                .lastName("Doe")
                .birthdate(LocalDate.of(1990, 5, 15))
                .biography("Developer from Moscow")
                .city("Moscow")
                .passwordHash("\$2a\$12\$5Z8zM6L9Yf7q1w0Kj3rT.6VbQeDcXwRtjJ7mNpOqRsSvk1S2hL5G")
                .build()
    }
}
