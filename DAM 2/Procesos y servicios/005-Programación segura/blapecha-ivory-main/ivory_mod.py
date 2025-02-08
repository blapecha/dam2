import geoip2.database
import ipaddress
import os

def get_country(ip, reader):
    try:
        response = reader.country(ip)
        return response.country.name
    except geoip2.errors.AddressNotFoundError:
        return "Unknown"
    except ValueError:
        return "Invalid IP"
    except Exception as e:
        return f"Error: {e}"

def block_ips_htaccess(blacklisted_ips, htaccess_path):
    start_marker = "# BEGIN Blocked IPs by Country"
    end_marker = "# END Blocked IPs by Country"

    if os.path.exists(htaccess_path):
        with open(htaccess_path, 'r') as f:
            lines = f.readlines()
    else:
        lines = []

    existing_ips = set()
    within_block = False
    block_start_index = None
    block_end_index = None

    for index, line in enumerate(lines):
        if line.strip() == start_marker:
            within_block = True
            block_start_index = index
            continue
        if line.strip() == end_marker:
            within_block = False
            block_end_index = index
            break
        if within_block and line.strip().startswith("Require not ip"):
            ip = line.strip().split("Require not ip")[-1].strip()
            existing_ips.add(ip)

    new_ips = set(blacklisted_ips) - existing_ips
    if not new_ips:
        print("No new IPs to add to the country-based block.")
        return

    block_rules = []
    if block_start_index is not None and block_end_index is not None:
        for ip in new_ips:
            block_rules.append(f"    Require not ip {ip}\n")
        new_lines = lines[:block_end_index] + block_rules + lines[block_end_index:]
    else:
        block_rules = [start_marker + "\n", "<RequireAll>\n", "    Require all granted\n"]
        for ip in new_ips:
            block_rules.append(f"    Require not ip {ip}\n")
        block_rules.append("</RequireAll>\n")
        block_rules.append(end_marker + "\n")
        new_lines = lines + ["\n"] + block_rules

    try:
        with open(htaccess_path, 'w') as f:
            f.writelines(new_lines)
        print(f"Successfully updated {htaccess_path} with new blocked IPs by country.")
    except Exception as e:
        print(f"Error writing to {htaccess_path}: {e}")

def main():
    log_file_path = "C:/xampp/apache/logs/jocarsa-oldlace-access.log"
    mmdb_path = 'GeoLite2-Country.mmdb'
    non_desired_countries = ["China", "Ukraine", "Singapore"]
    htaccess_path = "C:/xampp/htdocs/.htaccess"

    try:
        if os.path.exists(htaccess_path):
            backup_path = htaccess_path + ".backup_country"
            with open(htaccess_path, 'r') as original, open(backup_path, 'w') as backup:
                backup.write(original.read())
            print(f"Backup of .htaccess created at {backup_path}")
    except Exception as e:
        print(f"Error creating backup of .htaccess: {e}")
        return

    try:
        with open(log_file_path, 'r') as archivo:
            lineas = archivo.readlines()
    except FileNotFoundError:
        print(f"Error: The file {log_file_path} does not exist.")
        return
    except Exception as e:
        print(f"An error occurred while reading the file: {e}")
        return

    diccionario_ips = {}
    for linea in lineas:
        try:
            ip = linea.split("-")[0].strip()
            ipaddress.IPv4Address(ip)
            diccionario_ips[ip] = diccionario_ips.get(ip, 0) + 1
        except ipaddress.AddressValueError:
            continue

    try:
        reader = geoip2.database.Reader(mmdb_path)
    except FileNotFoundError:
        print(f"Error: The GeoLite2 database file {mmdb_path} does not exist.")
        return
    except Exception as e:
        print(f"An error occurred while opening the GeoLite2 database: {e}")
        return

    diccionario_paises = {}
    unknown_ips = []
    blacklisted_ips = {}

    for ip, count in diccionario_ips.items():
        country = get_country(ip, reader)
        if country not in ["Unknown", "Invalid IP", "Error"]:
            if country in non_desired_countries:
                blacklisted_ips[ip] = count
            else:
                diccionario_paises[country] = diccionario_paises.get(country, 0) + count
        else:
            unknown_ips.append(ip)

    reader.close()

    ordenado_paises = dict(sorted(diccionario_paises.items(), key=lambda item: item[1], reverse=True))
    ordenado_blacklisted = dict(sorted(blacklisted_ips.items(), key=lambda item: item[1], reverse=True))

    # *** MODIFICACIÓN: Mostrar el número total de IPs analizadas ***
    print(f"\nTotal IPs analizadas: {len(diccionario_ips)}")

    print("Desired Countries:")
    for country, count in ordenado_paises.items():
        print(f"{country}: {count}")

    print("\nBlacklisted Countries:")
    for ip, count in ordenado_blacklisted.items():
        print(f"{ip}: {count}")

    print(f"\nUnknown/Invalid: {len(unknown_ips)}")

    try:
        with open('unknown_ips_country.log', 'w') as f:
            for ip in unknown_ips:
                f.write(f"{ip}\n")
        print("Unknown IPs logged to unknown_ips_country.log")
    except Exception as e:
        print(f"Error writing unknown IPs to file: {e}")

    try:
        with open('blacklisted_ips_country.log', 'w') as f:
            for ip, count in ordenado_blacklisted.items():
                f.write(f"{ip} - Count: {count}\n")
        print("Blacklisted IPs by country logged to blacklisted_ips_country.log")
    except Exception as e:
        print(f"Error writing blacklisted IPs to file: {e}")

    if ordenado_blacklisted:
        block_ips_htaccess(ordenado_blacklisted.keys(), htaccess_path)
    else:
        print("No blacklisted IPs by country to block.")
