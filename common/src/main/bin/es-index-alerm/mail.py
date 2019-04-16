#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys
from exchangelib import DELEGATE, Account, Credentials, Message, Mailbox, HTMLBody

def Email(to, subject, body):
    creds = Credentials(
        username='caoke',
        password='Ellypope.1'
    )
    account = Account(
        primary_smtp_address='caoke@koolearn-inc.com',
        credentials=creds,
        autodiscover=True,
        access_type=DELEGATE
    )
    m = Message(
        account=account,
        subject=subject,
        body=HTMLBody(body),
        to_recipients = [Mailbox(email_address=to)]
    )
    m.send()
message=sys.argv[1]
Email("caoke@koolearn-inc.com", "es index alarm", message)