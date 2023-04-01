package android.template.ui.navigation

sealed class NavDestination(val route: String) {
    object FirstScreen: NavDestination("firstScreen")
    object SecondScreen: NavDestination("secondScreen")
}
