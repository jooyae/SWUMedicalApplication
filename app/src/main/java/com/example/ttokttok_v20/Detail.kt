package com.example.ttokttok_v20

data class Detail (val brandName: String? = null, // 브랜드 이름
                   val productName: String? = null, // 제품명
                   val function: String? = null, // 기능성
                   val warning: String? = null, // 주의사항
                   val content: String? = null, // 함량(세부 g)
                   val nutrient: String? = null) // 원재료