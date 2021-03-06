/*
 * MIT License
 *
 * Copyright (c) 2021 Pradyot Prakash
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/
package com.project.pradyotprakash.whatsappcompose.models

import com.google.firebase.firestore.DocumentReference

/**
 * User details
 */
data class User(
    var accountCreatedOn: Long = 0,
    var appVersion: String = "",
    var deviceId: String = "",
    var deviceModel: String = "",
    var deviceOs: String = "",
    var lastLoggedIn: Long = 0,
    var profilePic: String = "",
    var userId: String = "",
    var userName: String = "",
    var name: String = "",
    var status: String = "",
    var phoneNumber: String = "",
    var countryNameCode: String = "",
    var countryPhoneCode: String = "",
    var countryName: String = "",
    var isDetailsAdded: Boolean = false,
    var emailId: String = ""
)

/**
 * Status details for firestore
 */
data class StatusFirestore(
    var statusMessage: String = "",
    var createdBy: DocumentReference? = null,
    var appVersion: String = "",
    var deviceId: String = "",
    var deviceModel: String = "",
    var deviceOs: String = "",
    var createdOn: Long = 0
)

/**
 * Status details
 */
data class Status(
    var statusMessage: String = "",
    var createdBy: DocumentReference? = null,
    var appVersion: String = "",
    var deviceId: String = "",
    var deviceModel: String = "",
    var deviceOs: String = "",
    var createdOn: Long = 0,
    var userDetails: User = User()
)

/**
 * Status division
 */
data class StatusDivision(
    var createdBy: DocumentReference? = null,
    var status: MutableList<Status> = mutableListOf(),
    var userDetails: User = User()
)

/**
 * Chat details for firestore
 */
data class ChatDetailsFirestore(
    var lastMessage: String = "",
    var lastMessageSentOn: Long = 0,
    var lastMessageSentBy: DocumentReference? = null,
    var chatCreatedOn: Long = 0,
    var chatCreatedBy: DocumentReference? = null,
    var chatIsFavourite: Boolean = false,
    var chatLastMessageRead: Boolean = false,
    var members: List<DocumentReference>? = null,
    var chatIsAGroup: Boolean = false
)

/**
 * Chat details
 */
data class ChatDetails(
    var lastMessage: String = "",
    var lastMessageSentOn: Long = 0,
    var lastMessageSentBy: DocumentReference? = null,
    var chatCreatedOn: Long = 0,
    var chatCreatedBy: DocumentReference? = null,
    var chatIsFavourite: Boolean = false,
    var chatLastMessageRead: Boolean = false,
    var members: List<DocumentReference>? = null,
    var chatIsAGroup: Boolean = false,
    var lastMessageSentOnString: String = "",
    var isLastMessageByCurrentUser: Boolean = false,
    var membersUser: ArrayList<User> = ArrayList(),
    var singleChatListDetails: User = User()
)

/**
 * Message details for the firestore
 */
data class MessageDetailsFirestore(
    var message: String = "",
    var sentOn: Long = 0,
    var sentBy: DocumentReference? = null
)

/**
 * Message details
 */
data class MessageDetails(
    var message: String = "",
    var sentOn: Long = 0,
    var sentBy: DocumentReference? = null,
    var messageByCurrentUser: Boolean = false
)
