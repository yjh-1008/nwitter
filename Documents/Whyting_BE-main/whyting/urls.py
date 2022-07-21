from django.contrib import admin
from django.urls import path
from whytingapp import views
from accounts import views as accounts_views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', views.home, name='home'),
    path('login/', accounts_views.login, name='login'),
    path('logout/', accounts_views.logout, name='logout'),
    path('register_owner/', accounts_views.register_owner, name='register_owner'),
    path('register_customer/', accounts_views.register_customer, name='register_customer'),
]
