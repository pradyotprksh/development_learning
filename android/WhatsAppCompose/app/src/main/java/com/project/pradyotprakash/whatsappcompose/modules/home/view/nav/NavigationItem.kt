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
package com.project.pradyotprakash.whatsappcompose.modules.home.view.nav

import com.project.pradyotprakash.whatsappcompose.R
import com.project.pradyotprakash.whatsappcompose.nav.Destination
import com.project.pradyotprakash.whatsappcompose.utils.Constants.chat
import com.project.pradyotprakash.whatsappcompose.utils.Constants.search
import com.project.pradyotprakash.whatsappcompose.utils.Constants.status

/**
 * A navigator item class which will be used as a navigation for bottom bar in home screen
 */
sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Chat : NavigationItem(Destination.Chat, R.mipmap.ic_chat, chat)
    object Search : NavigationItem(Destination.Search, R.mipmap.ic_search, search)
    object Status : NavigationItem(Destination.Status, R.mipmap.ic_status, status)
}
