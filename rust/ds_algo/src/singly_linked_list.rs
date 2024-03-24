/*
We define a generic struct Node<T> to represent a node in the singly 
linked list. It has two fields: data to hold the data of type T, and
next to hold a reference to the next node, wrapped in an Option<Box<Node<T>>>. 
Box is used for heap allocation to ensure each node has a fixed size.
*/
struct Node<T> {
    data: T,
    /*
    In Rust, `Option<Box<Node<T>>>` is a type used to represent an optional 
    reference to another node in a linked list. Let's break it down:

    - `Node<T>`: This is the type representing a node in the linked list. 
        It's a generic struct that can hold any type `T` as its data.
  
    - `Box<Node<T>>`: The `Box` type is a smart pointer that points to 
        heap-allocated data. In this case, it's used to dynamically allocate 
        memory for each node, allowing for flexible node creation and 
        deletion. The `Node<T>` struct is wrapped in a `Box` to make it a 
        heap-allocated value.

    - `Option<Box<Node<T>>>`: The `Option` enum is used to represent the 
        presence or absence of a value. It has two variants: `Some`, which 
        holds a value, and `None`, which represents absence. In this case, 
        `Option` is used to indicate whether there is a reference to another 
        node (`Some`) or not (`None`). If the current node is the last node 
        in the list, its `next` field will be `None`. If there is another node 
        after it, its `next` field will be `Some`, containing a `Box` pointer 
        to the next node.

    So, `Option<Box<Node<T>>>` is a way of expressing that a node in a linked 
    list may or may not have a reference to another node. If it does have a 
    reference, that reference is wrapped in a `Box` to manage memory allocation, 
    and it's wrapped in an `Option` to handle the case where there's no next node.
     */
    next: Option<Box<Node<T>>>,
}

impl<T> Node<T> {
    fn new(data: T) -> Self {
        Node { data, next: None }
    }

    fn append(&mut self, data: T) {
        let mut current = self;
        while let Some(ref mut next_node) = current.next {
            current = next_node;
        }
        current.next = Some(Box::new(Node::new(data)));
    }

    /*
    In the method signature `fn print_list(&self) where T: std::fmt::Display`, `fn` 
    declares a function, `print_list` is the name of the function, `&self` is the 
    method receiver indicating that this method is meant to be called on an instance 
    of the struct, and `where T: std::fmt::Display` is a trait bound specifying that 
    `T` must implement the `std::fmt::Display` trait.

    Breaking it down:

    - `&self`: This indicates that the method takes a reference to `self`, meaning it 
    can access the data of the struct instance but cannot modify it. The `&self` syntax 
    is used for methods that don't need to mutate the struct.
  
    - `where T: std::fmt::Display`: This is a trait bound specifying that the generic 
    type `T` must implement the `std::fmt::Display` trait. The `Display` trait is used 
    for types that can be formatted as strings. By requiring `T` to implement `Display`, 
    we can use the `{}` format specifier in `println!` to print the data of type `T`.

    Putting it together, `fn print_list(&self) where T: std::fmt::Display` declares a 
    method named `print_list` that can be called on instances of the struct. It prints 
    the contents of the linked list, assuming that the data stored in each node (`T`) 
    can be formatted as a string. This ensures that `println!("{}", node.data)` inside 
    the method works correctly for any type `T` that implements `Display`.
     */
    fn print_list(&self) where T: std::fmt::Display {
        let mut current = Some(self);
        while let Some(node) = current {
            print!("[{}]->", node.data);
            current = node.next.as_deref();
        }
        println!();
    }
}

pub fn singly_linked_list_example() {
    /*
    let mut head = Node {
        data: 1,
        next: None,
    };

    head.next = Some(
        Box::new(
            Node { 
                data: 2, 
                next: Some(
                    Box::new(
                        Node {
                            data: 3,
                            next: None,
                        }
                    )
                ), 
            }
        )
    );

    head.print_list();
     */

    let mut head = Node::new(1);
    head.append(2);
    head.append(3);
    head.print_list();
}