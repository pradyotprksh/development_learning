from setuptools import setup, find_packages
import pathlib

here = pathlib.Path(__file__).parent.resolve()

long_description = (here / 'README.md').read_text(encoding='utf-8')

setup(
    name='basics',
    version='1.0.0',
    description='A Python project which contains all my learnings',
    long_description=long_description,
    author='Pradyot Prakash',
    author_email='pradyotprksh4@gmail.com',
    keywords='basics, ds, algorithms',
    data_files=[(
        'data', ['data/']
    )],
    packages=find_packages(
        include=[
            'basics', 'basics.objects_ds', 'basics.python_statements', 'basics.methods_functions', 'basics.tic_tac_toe',
            'basics.object_oriented_programming', 'basics.errors_exception_handling', 'basics.testing', 'basics.war',
            'basics.blackjack', 'basics.decorators'
            'src',
        ]
    ),
    install_requires=[
        'loguru', 'pylint'
    ],
    entry_points={
        'console_scripts': [
            'basics = basics:main'
        ]
    }
)
