package com.example.martocv.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.martocv.ui.feature_about.screen.AboutScreen
import com.example.martocv.ui.feature_experience.screen.ExperienceDetailScreen
import com.example.martocv.ui.feature_experience.screen.ExperienceScreen
import com.example.martocv.ui.feature_formation.screen.FormationScreen
import kotlinx.serialization.Serializable

val bottomNavItems = listOf(
    BaseScreen.AboutDestination,
    BaseScreen.ExperienceDestination,
    BaseScreen.FormationDestination,
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                BaseScreen.AboutDestination -> Icon(
                                    Icons.Filled.AccountCircle,
                                    contentDescription = screen.label
                                )

                                BaseScreen.ExperienceDestination -> Icon(
                                    Icons.Filled.Work,
                                    contentDescription = screen.label
                                )

                                BaseScreen.FormationDestination -> Icon(
                                    Icons.Filled.School,
                                    contentDescription = screen.label
                                )
                            }
                        },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BaseScreen.AboutDestination,
            modifier = Modifier
                // Appliquer UNIQUEMENT le padding fourni par Scaffold
                .padding(innerPadding)
                .fillMaxSize(), // Bonne pratique,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400)
                )
            }

        ) {
            composable<BaseScreen.AboutDestination> {
                AboutScreen()
            }
            composable<BaseScreen.ExperienceDestination> {
                ExperienceScreen(onExperienceClick = {
                    navController.navigate(
                        ExperienceDetailDestination(
                            it
                        )
                    )
                })
            }
            composable<BaseScreen.FormationDestination> {
                FormationScreen()
            }
            composable<ExperienceDetailDestination> {
                val args = it.toRoute<ExperienceDetailDestination>()
                ExperienceDetailScreen(
                    experienceId = args.experienceId,
                    onBack = {
                        navController.popBackStack()
                    })
            }
        }
    }
}

@Serializable
sealed class BaseScreen(val label: String, val route: String) {

    @Serializable
    object AboutDestination : BaseScreen("À Propos", "com.example.martocv.ui.navigation.BaseScreen.AboutDestination")

    @Serializable
    object ExperienceDestination : BaseScreen("Expériences", "com.example.martocv.ui.navigation.BaseScreen.ExperienceDestination")

    @Serializable
    object FormationDestination : BaseScreen("Formation", "com.example.martocv.ui.navigation.BaseScreen.FormationDestination")
}

@Serializable
data class ExperienceDetailDestination(
    val experienceId: String = ""
)