package org.kistenjunge.grails.scoreboard



import org.junit.*
import grails.test.mixin.*

@TestFor(GamerController)
@Mock(Gamer)
class GamerControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gamer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gamerInstanceList.size() == 0
        assert model.gamerInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gamerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.gamerInstance != null
        assert view == '/gamer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gamer/show/1'
        assert controller.flash.message != null
        assert Gamer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gamer/list'


        populateValidParams(params)
        def gamer = new Gamer(params)

        assert gamer.save() != null

        params.id = gamer.id

        def model = controller.show()

        assert model.gamerInstance == gamer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gamer/list'


        populateValidParams(params)
        def gamer = new Gamer(params)

        assert gamer.save() != null

        params.id = gamer.id

        def model = controller.edit()

        assert model.gamerInstance == gamer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gamer/list'

        response.reset()


        populateValidParams(params)
        def gamer = new Gamer(params)

        assert gamer.save() != null

        // test invalid parameters in update
        params.id = gamer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gamer/edit"
        assert model.gamerInstance != null

        gamer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gamer/show/$gamer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gamer.clearErrors()

        populateValidParams(params)
        params.id = gamer.id
        params.version = -1
        controller.update()

        assert view == "/gamer/edit"
        assert model.gamerInstance != null
        assert model.gamerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gamer/list'

        response.reset()

        populateValidParams(params)
        def gamer = new Gamer(params)

        assert gamer.save() != null
        assert Gamer.count() == 1

        params.id = gamer.id

        controller.delete()

        assert Gamer.count() == 0
        assert Gamer.get(gamer.id) == null
        assert response.redirectedUrl == '/gamer/list'
    }
}
