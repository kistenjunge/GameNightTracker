package org.kistenjunge.grails.scoreboard



import org.junit.*
import grails.test.mixin.*

@TestFor(ScoreController)
@Mock(Score)
class ScoreControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/score/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scoreInstanceList.size() == 0
        assert model.scoreInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.scoreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scoreInstance != null
        assert view == '/score/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/score/show/1'
        assert controller.flash.message != null
        assert Score.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/score/list'


        populateValidParams(params)
        def score = new Score(params)

        assert score.save() != null

        params.id = score.id

        def model = controller.show()

        assert model.scoreInstance == score
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/score/list'


        populateValidParams(params)
        def score = new Score(params)

        assert score.save() != null

        params.id = score.id

        def model = controller.edit()

        assert model.scoreInstance == score
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/score/list'

        response.reset()


        populateValidParams(params)
        def score = new Score(params)

        assert score.save() != null

        // test invalid parameters in update
        params.id = score.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/score/edit"
        assert model.scoreInstance != null

        score.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/score/show/$score.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        score.clearErrors()

        populateValidParams(params)
        params.id = score.id
        params.version = -1
        controller.update()

        assert view == "/score/edit"
        assert model.scoreInstance != null
        assert model.scoreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/score/list'

        response.reset()

        populateValidParams(params)
        def score = new Score(params)

        assert score.save() != null
        assert Score.count() == 1

        params.id = score.id

        controller.delete()

        assert Score.count() == 0
        assert Score.get(score.id) == null
        assert response.redirectedUrl == '/score/list'
    }
}
