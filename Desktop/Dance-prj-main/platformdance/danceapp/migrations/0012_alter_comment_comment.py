# Generated by Django 4.0.6 on 2022-08-12 08:56

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('danceapp', '0011_post_hits'),
    ]

    operations = [
        migrations.AlterField(
            model_name='comment',
            name='comment',
            field=models.TextField(max_length=100, verbose_name=''),
        ),
    ]
