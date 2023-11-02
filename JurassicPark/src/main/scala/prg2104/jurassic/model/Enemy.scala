package prg2104.jurassic.model

//Enemy object stores the dinosaurs for each of the levels (easy and hard)
object Enemy{
    //list of enemy
    
    //easy enemy
    var e1 = new Trex
    var e2 = new Pachycephalosaurus
    var e3 = new Pteranodon
    var e4 = new Triceratops
    var e5 = new Spinosaurus
    var e6 = new Sarcosuchus
    var easyEnemy:List[Dinosaur] = List(e1,e2,e3,e4,e5,e6)

    //hard enemy
    var h1 = new Trex(3,3)
    var h2 = new Pachycephalosaurus(3,3)
    var h3 = new Pteranodon(3,3)
    var h4 = new Triceratops(3,3)
    var h5 = new Spinosaurus(3,3)
    var h6 = new Sarcosuchus(3,3)
    var hardEnemy:List[Dinosaur] = List(h1,h2,h3,h4,h5,h6)

    def reset()={
        e1 = new Trex
        e2 = new Pachycephalosaurus
        e3 = new Pteranodon
        e4 = new Triceratops
        e5 = new Spinosaurus
        e6 = new Sarcosuchus

        h1 = new Trex(3,3)
        h2 = new Pachycephalosaurus(3,3)
        h3 = new Pteranodon(3,3)
        h4 = new Triceratops(3,3)
        h5 = new Spinosaurus(4,4)
        h6 = new Sarcosuchus(5,5)

        easyEnemy = List(e1,e2,e3,e4,e5,e6)
        hardEnemy = List(h1,h2,h3,h4,h5,h6)
    }

}