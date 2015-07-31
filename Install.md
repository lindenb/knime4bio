Install KNime4Bio

# Installing from an update site #

(see also: http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.user%2Ftasks%2Ftasks-34.htm , )


  * Download Knime from http://www.knime.org/download
  * Open knime
  * Menu Help > Install New Software. The install dialog will appear.
  * Click on "Add..."
    * Name:  KNime4BIO
    * URL: http://redonlab.univ-nantes.fr/public_html/eclipse/plugins/fr.inserm.umr915.knime4ngs.update/
    * press OK
  * Browse the available nodes in the trees and find the latest version of knime4bio (at the time of this writing the version is **1.0.30** ) , accept the license, finish.

If you need to update your workbench:

  * Menu File > "Update Knime..."



# Installing the Knime4Bio Plugin from a local zip #

**warning**: the current archive is **outdated**

As an alternative to installing from the update site, you can also install the Knime4Bio Plugin by downloading the update site as an archive and installing from it.

  1. Download Knime from http://www.knime.org/download
  1. Download the plugin from the download area: http://code.google.com/p/knime4bio/downloads/list
  1. Unzip the archive.
  1. In Knime, choose Help > Install New Software...
  1. In the "Work with" section, click the Add... button. The "Add Repository" dialog box appears.
  1. Click Local and select the directory you unzipped, then click OK. Its path appears in the "Location" field. Leave the "Name" field empty.

# Configure the Proxy/Firewall #

If you're working behind a **proxy/firewall**, you'll need to configure the proxy. Go to
```
File/preferences/Network
```
> and change the parameters.

The process is explained below:

http://www.mkyong.com/web-development/how-to-configure-proxy-settings-in-eclipse/