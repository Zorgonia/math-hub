package com.kyang.mathhub.navigation

enum class MathAppScreen(val route: String) {
    MathQuestion("screen_math_question"),
    MathAnswer("screen_math_answer"),
    MathOptions("screen_math_options"),
    MathGameEnd("screen_math_end"),
    MathHome("screen_math_home")
}

enum class TipScreen(val route: String) {
    TipHome("screen_tip_home"),
    TipDetail("screen_tip_detail")
}

enum class HistoryScreen(val route: String) {
    HistoryHome("screen_history_home"),
    HistoryDetail("screen_history_detail")
}