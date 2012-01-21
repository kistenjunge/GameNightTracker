package org.kistenjunge.grails.scoreboard



import org.junit.*
import grails.test.mixin.*

@TestFor(ResultController)
@Mock(Result)
class ResultControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/result/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.resultInstanceList.size() == 0
        assert model.resultInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.resultInstance != null
    }

    void testSave() {
        controller.save()

        assert model.resultInstance != null
        assert view == '/result/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/result/show/1'
        assert controller.flash.message != null
        assert Result.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/result/list'


        populateValidParams(params)
        def result = new Result(params)

        assert result.save() != null

        params.id = result.id

        def model = controller.show()

        assert model.resultInstance == result
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/result/list'


        populateValidParams(params)
        def result = new Result(params)

        assert result.save() != null

        params.id = result.id

        def model = controller.edit()

        assert model.resultInstance == result
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/result/list'

        response.reset()


        populateValidParams(params)
        def result = new Result(params)

        assert result.save() != null

        // test invalid parameters in update
        params.id = result.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/result/edit"
        assert model.resultInstance != null

        result.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/result/show/$result.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        result.clearErrors()

        populateValidParams(params)
        params.id = result.id
        params.version = -1
        controller.update()

        assert view == "/result/edit"
        assert model.resultInstance != null
        assert model.resultInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/result/list'

        response.reset()

        populateValidParams(params)
        def result = new Result(params)

        assert result.save() != null
        assert Result.count() == 1

        params.id = result.id

        controller.delete()

        assert Result.count() == 0
        assert Result.get(result.id) == null
        assert response.redirectedUrl == '/result/list'
    }
}
