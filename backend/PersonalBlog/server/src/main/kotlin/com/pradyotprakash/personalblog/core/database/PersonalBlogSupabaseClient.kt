package com.pradyotprakash.personalblog.core.database

import com.pradyotprakash.personalblog.utils.Constants.Keys.SUPABASE_API_KEY
import com.pradyotprakash.personalblog.utils.Constants.Keys.SUPABASE_URL
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

object PersonalBlogSupabaseClient {
    private val supabase = createSupabaseClient(
        supabaseUrl = System.getenv(SUPABASE_URL),
        supabaseKey = System.getenv(SUPABASE_API_KEY),
    ) {
        install(Postgrest)
    }

    fun getDatabase() = supabase.postgrest
}