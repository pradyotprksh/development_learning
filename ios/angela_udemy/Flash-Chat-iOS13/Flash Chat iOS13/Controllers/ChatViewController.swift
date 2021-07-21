//
//  ChatViewController.swift
//  Flash Chat iOS13
//
//  Created by Angela Yu on 21/10/2019.
//  Copyright © 2019 Angela Yu. All rights reserved.
//

import UIKit
import Firebase

class ChatViewController: UIViewController {
    // Outlets in the controller
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var messageTextfield: UITextField!
    
    // Messages
    var messages: [Message] = []
    
    // Firebase firestore
    let db = Firestore.firestore()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.dataSource = self
        
        navigationItem.hidesBackButton = true
        
        tableView.register(UINib(nibName: Constants.cellNibName, bundle: nil), forCellReuseIdentifier: Constants.cellIdentifier)
        
        loadMessages()
    }
    
    // Get messages from firestore
    func loadMessages() {
        db.collection(Constants.FStore.collectionName)
            .order(by: Constants.FStore.dateField)
            .addSnapshotListener { snapshot, error in
            if let err = error {
                print(err)
            } else {
                if let snapshotDocuments = snapshot?.documents {
                    self.messages = []
                    for doc in snapshotDocuments {
                        let data = doc.data()
                        if let sender = data[Constants.FStore.senderField] as? String,
                           let body = data[Constants.FStore.bodyField] as? String {
                            self.messages.append(
                                Message(
                                    sender: sender,
                                    body: body
                                )
                            )
                        }
                    }
                    DispatchQueue.main.async {
                        self.tableView.reloadData()
                        
                        let indexPath = IndexPath(row: self.messages.count - 1, section: 0)
                        self.tableView.scrollToRow(at: indexPath, at: .top, animated: true)
                    }
                }
            }
        }
    }
    
    // Actions in the controller
    //
    // An action which is triggered when the send button is pressed
    @IBAction func sendPressed(_ sender: UIButton) {
        if let messageBody = messageTextfield.text, let messageSender = Auth.auth().currentUser?.email {
            db.collection(Constants.FStore.collectionName).addDocument(
                data: [
                    Constants.FStore.bodyField: messageBody,
                    Constants.FStore.senderField: messageSender,
                    Constants.FStore.dateField: Date().timeIntervalSince1970
                ]
            ) { error in
                if let err = error {
                    print(err)
                } else {
                    self.messageTextfield.text = ""
                }
            }
        }
    }
    //
    // An action which is triggered when the log out button is pressed
    @IBAction func logOutPressed(_ sender: UIBarButtonItem) {
        do {
            try Auth.auth().signOut()
            navigationController?.popToRootViewController(animated: true)
        } catch let signOutError as NSError {
            print("Error signing out: %@", signOutError)
        }
    }
}

//MARK:- UITableViewDataSource

extension ChatViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return messages.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: Constants.cellIdentifier, for: indexPath) as! MessageCell
        cell.label?.text = messages[indexPath.row].body
        
        if let messageSender = Auth.auth().currentUser?.email {
            let sender = messages[indexPath.row].sender
            if messageSender == sender {
                cell.leftImageView.isHidden = true
                cell.rightImageView.isHidden = false
                cell.messageBubble.backgroundColor = UIColor(named: Constants.BrandColors.lighBlue)
                cell.label.textColor = UIColor(named: Constants.BrandColors.blue)
            } else {
                cell.rightImageView.isHidden = true
                cell.leftImageView.isHidden = false
                cell.messageBubble.backgroundColor = UIColor(named: Constants.BrandColors.purple)
                cell.label.textColor = UIColor(named: Constants.BrandColors.lightPurple)
            }
        }
        
        return cell
    }
}
