package ch6

/**
 * Multiline strings
 */

memo = '''Several of you raised concerns about long meetings.
To discuss this, we will be holding a 3 hour meeting starting
at 9AM tomorrow. All getting this memo are required to attend.
If you can't make it, please have a meeting with your manager to explain.
'''
println memo

price = 251.12
message = """We're very pleased to announce
that our stock price hit a high of \$${price} per share
on December 24 th.Great news in time for ...
"""
println message

langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
content = ''
langs.each {language, author ->
    fragment = """
    <language name = "${language}">
        <author> ${author} </author>
    </language>
            """
    content += fragment
}
xml = "<languages>${content}</languages>"
println xml
