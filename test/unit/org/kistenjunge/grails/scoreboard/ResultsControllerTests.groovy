package org.kistenjunge.grails.scoreboard



import org.junit.*
import grails.test.mixin.*

@TestFor(ResultsController)
@Mock(Results)
class ResultsControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/results/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.resultsInstanceList.size() == 0
        assert model.resultsInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.resultsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.resultsInstance != null
        assert view == '/results/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/results/show/1'
        assert controller.flash.message != null
        assert Results.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/results/list'


        populateValidParams(params)
        def results = new Results(params)

        assert results.save() != null

        params.id = results.id

        def model = controller.show()

        assert model.resultsInstance == results
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/results/list'


        populateValidParams(params)
        def results = new Results(params)

        assert results.save() != null

        params.id = results.id

        def model = controller.edit()

        assert model.resultsInstance == results
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/results/list'

        response.reset()


        populateValidParams(params)
        def results = new Results(params)

        assert results.save() != null

        // test invalid parameters in update
        params.id = results.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/results/edit"
        assert model.resultsInstance != null

        results.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/results/show/$results.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        results.clearErrors()

        populateValidParams(params)
        params.id = results.id
        params.version = -1
        controller.update()

        assert view == "/results/edit"
        assert model.resultsInstance != null
        assert model.resultsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/results/list'

        response.reset()

        populateValidParams(params)
        def results = new Results(params)

        assert results.save() != null
        assert Results.count() == 1

        params.id = results.id

        controller.delete()

        assert Results.count() == 0
        assert Results.get(results.id) == null
        assert response.redirectedUrl == '/results/list'
    }
}
