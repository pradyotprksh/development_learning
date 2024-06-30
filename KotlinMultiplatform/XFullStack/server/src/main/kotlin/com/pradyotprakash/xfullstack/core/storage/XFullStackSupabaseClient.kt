package com.pradyotprakash.xfullstack.core.storage

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import utils.Constants.Keys.SUPABASE_API_KEY
import utils.Constants.Keys.SUPABASE_URL

object XFullStackSupabaseClient {
    private val supabase = createSupabaseClient(
        supabaseUrl = System.getenv(SUPABASE_URL),
        supabaseKey = System.getenv(SUPABASE_API_KEY),
    ) {
        install(Storage)
    }

    fun getStorage() = supabase.storage
}