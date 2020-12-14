package com.tree.bst;

import org.springframework.web.bind.annotation.*;

class AddNodeInput {
    public int data;
}

@CrossOrigin(origins = "*")
@RestController
public class HttpServer {

    BSTTree tree;

    public HttpServer(){
        this.tree = makeBST();
    }

    @PostMapping("/tree")
    BSTTree addNode(@RequestBody AddNodeInput input) {
        tree.addNode(input.data);

        return tree;
    }

    @GetMapping("/tree/node/{id}")
    String get(@PathVariable Long id) {

        System.out.println("INPUT: " + id);

        return "mumbo jumbo";
    }

    @GetMapping("/tree")
    BSTTree getTree(){
        return tree;
    }

    @RequestMapping(path="/tree/{id}")
    public boolean getHasNode(@PathVariable("id") int id){
        tree.transverseDFIterative();

        System.out.println("HAS NODE " + id + " = " + tree.hasNode(id));

        return tree.hasNode(id);
    }

    @GetMapping("/colors")
    String[] getColorList(){
        return new String[]{"red", "green", "black", "orange", "purple", "pink", "blue", "grey", "brown", "yellow", "white"};
    }

    private BSTTree makeBST() {

        BSTTree tree = new BSTTree();
        //level one
        tree.addNode(100);

        //level two
        tree.addNode(50);
        tree.addNode(150);

        //level three
        tree.addNode(25);
        tree.addNode(75);

        tree.addNode(125);
        tree.addNode(175);

        return tree;
    }
}
