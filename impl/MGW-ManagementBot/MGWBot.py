import asyncio
import random

import discord
from colorama import Fore

# Variabels
from discord.ext import commands

client = discord.Client()
clientUser = discord.ClientUser
split = '-------------------------------------------------------------------------------'
threadCount = 1

@client.event
async def on_ready():
    await client.change_presence(activity=discord.Game('Erledigt Papierkram...'), status=discord.Status.online)

    print(Fore.GREEN + split)
    print(Fore.GREEN + 'Bot enabled...')
    print(Fore.GREEN + 'Logged in as: ' + str(client.user.id) + ' | ' + str(client.user.name))
    print(Fore.GREEN + 'Set Activity!')
    print(Fore.GREEN + split)

@client.event
async def on_message(message):
    if message.author.bot:
        return

    if message.channel.name == '❌bug-report❌':
        if 'Titel: ' in message.content and 'Beschreibung:' in message.content and 'Fehler Meldung/Code:' in message.content and 'Welches System Nutze ich:' in message.content:
            await message.add_reaction('\N{CROSS MARK}')
        else:
            await message.delete()
            msg = await message.channel.send('**Dieser Bug Report ist nicht voll ständig bitte gucke dir das beispiel an!**')

            await asyncio.sleep(3)
            await msg.delete()

    if '+help'.__eq__(message.content):
        if message.channel.id != '9616757881287803297' and message.channel.id != '957002586446237757':
            await message.delete()
            return

        await message.channel.send('**__MGW-Bot Hilfe__**\n'
                                   ' \n'
                                   '**Gennerelle hilfe:**\n'
                                   '-Command Prefx: **+**\n')

@client.event
async def on_voice_state_update(member, before, after):
    if after.channel is None or after.channel.name != 'Support Warteschlange':
        return

    for channel in client.get_all_channels():
        if before.channel.name == 'Support Warteschlange' or after.channel.name == 'Support Warteschlange':
            channel = client.get_channel(961700811686170654)

            await channel.send('<@&929805870341193748> **Eine person ist in dem Support Warteraum beigetreten!**'.format())
            return

@client.event
async def on_reaction_add(reaction, user):
    if user.bot:
        return

    if reaction.message.channel.name == '❌bug-report❌':
        await reaction.remove(user)
        emoji = reaction.emoji

        if(emoji == '❌'):
            for r in reaction.message.reactions:
                await reaction.message.clear_reaction(r)

            await reaction.message.add_reaction('✅')
            return

        if(emoji == '✅'):
            for r in reaction.message.reactions:
                await reaction.message.clear_reaction(r)

            await reaction.message.add_reaction('❌')
            return

client.run('No Token for you')
