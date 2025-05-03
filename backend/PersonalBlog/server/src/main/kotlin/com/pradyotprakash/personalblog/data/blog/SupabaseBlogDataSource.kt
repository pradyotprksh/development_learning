package com.pradyotprakash.personalblog.data.blog

import io.github.jan.supabase.postgrest.Postgrest

class SupabaseBlogDataSource(
    private val postgrest: Postgrest,
) : BlogDataSource {

}